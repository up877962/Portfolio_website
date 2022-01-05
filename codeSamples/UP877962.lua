--Connect to the local area network on station mode
wifi.setmode(wifi.STATION)
station_cfg={}
--insert your AP credentials
station_cfg.ssid="VM071645-2G"
station_cfg.pwd="nogames4penfold"

station_cfg.save=false
wifi.sta.config(station_cfg)
mytimer=tmr.create()
mytimer:register(5000, 1, function() 
   if wifi.sta.getip()==nil then
        print("Connecting to AP...\n")
   else
        ip, nm, gw=wifi.sta.getip()
        print("IP Info: \nIP Address: ",ip)
        print("Netmask: ",nm)
        print("Gateway Addr: ",gw,"\n")
        
   end 
end)




--this pin controls the buzzer
buzzPin=5
gpio.mode(5, gpio.OUTPUT)


--pin controls the LED
lightPin=2
gpio.mode(2, gpio.OUTPUT)
gpio.write(lightPin,gpio.LOW)


--used as a switch for the buzzer
flag=0

--pin for the dht11 sensor 
pinDHT=7

--controls the led on and off
function led(input)

if input == "ON1" then

lightPin=2
gpio.mode(2, gpio.OUTPUT)
gpio.write(lightPin,gpio.HIGH)


else if input == "OFF1" then
lightPin=2
gpio.mode(2, gpio.OUTPUT)
gpio.write(lightPin,gpio.LOW)


end
end

end





--starts the buzzer
function startBuzz()
if flag==1 then

gpio.write(buzzPin,gpio.HIGH)

end
end

--stops the buzzer
function stopBuzz()
flag=0
gpio.write(buzzPin,gpio.LOW)


end

motionPin=6
gpio.mode(motionPin, gpio.INT)

start=tmr.time()
lastEnd=0
count=0


--starts the motion sensor and keeps a count of times triggered and duration of trigger--limited by sensor
function startMotion()
  start=tmr.time()
  print('Motion detected!')
  count= count+1
  tmr.delay(1000000)
  gpio.trig(motionPin, "down", stopMotion)

  startBuzz()
end

--stops the motion sensor
function stopMotion()
  duration = tmr.time() - start
  print('motion ended after '..duration..' seconds.')
  tmr.delay(1000000)
  
  gpio.trig(motionPin, "up", startMotion)

  stopBuzz()
end

gpio.trig(motionPin, "up", startMotion)


mytimer:start()


--creates the TCP server
srv=net.createServer(net.TCP)
srv:listen(80,function(conn)
    conn:on("receive",function(conn,request)           
        local status, temp, humi , temp_dec, humi_dec = dht.read11(pinDHT)
        
        --buffer fot the html code
        local buf = ""
        if status == dht.OK then
            print("DHT Temperature:"..temp.." Humidity:"..humi.."\n")
        end
        buf=buf.."<html>"      
        buf=buf.."<head> <title>Room Sensor</title> <meta http-equiv=\"refresh\" content=\"3\"> </head>"
        buf = buf.."<h1>ESP8266 Room sensor</h1>"  
        buf=buf.."<body><p>Temperature: "..temp.."."..temp_dec.."C</p>"  
        buf=buf.."<p>Humidity: "..humi.."."..humi_dec.."%RH</p>" 
        
        --RE to match the on/off commands and post GET req
        local _, _, method, path, vars = string.find(request, "([A-Z]+) (.+)?(.+) HTTP")
        if(method == nil)then
            _, _, method, path = string.find(request, "([A-Z]+) (.+) HTTP")
        end
        local _GET = {}
        if (vars ~= nil)then
            for k, v in string.gmatch(vars, "(%w+)=(%w+)&*") do
                _GET[k] = v
            end
        end
        
        buf = buf.."<p>LED <a href=\"?pin=ON1\"><button>ON</button></a>&nbsp;<a href=\"?pin=OFF1\"><button>OFF</button></a></p>"
        buf = buf.."<p>Motion sensor Sound <a href=\"?pin=ON2\"><button>ON</button></a>&nbsp;<a href=\"?pin=OFF2\"><button>OFF</button></a></p>"
        local _on,_off = "",""
        if(_GET.pin == "ON1")then
              gpio.write(lightPin, gpio.HIGH)
        elseif(_GET.pin == "OFF1")then
              gpio.write(lightPin, gpio.LOW)
        elseif(_GET.pin == "ON2")then
              flag=1
              --gpio.write(switch, gpio.HIGH)
        elseif(_GET.pin == "OFF2")then
              --gpio.write(switch, gpio.LOW)
              flag=0
        end

        
        
        buf=buf.."<p>"..count.."movements detected".."</p></body></html>"
        conn:send(buf)
        conn:on("sent",function(conn) conn:close() end)         
    end)
end)
--Connect your mobile device to the AP and navigate to the IP address shown in the console
