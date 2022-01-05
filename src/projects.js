//################  Use the array below to add new files, follow the incremental numbering for ID's when adding new files ##################


const project_links = [

    {
    id: 1,
    link: "./codeSamples/SequentialPrimeSieve.java" ,
    description: "A sequential prime numer sieve written in java"

    },

    {
    id: 2,
    link: "./codeSamples/MultiCorePrimeSieve.java" ,
    description: "A multi-core prime sieve written in java"

    },

    {
    id: 3,
    link: "./codeSamples/MPI_PrimeSieve.java" ,
    description: "A MPI(message passing interface) prime sieve written in java"

    },

    {
    id: 4,
    link: "./codeSamples/UP877962.lua" ,
    description: "A room sensor that incorporates a motion, humidity and temperature sensor that can be controlled from a device on the local network. written in LUA and using an esp8266 microcontroller"

    },

     
    {
    id: 5,
    link: "./codeSamples/tableCreation.txt" ,
    description: "The code used to create a SQL database with dummy data and a selction of queries written for that database and data"

    }, 

    {
    id: 6,
    link: "./codeSamples/877962.txt" ,
    description: "The code used to create a patchwork pattern in python (uses a custom graphics library I do not own)"

    } 
];



// console.log(project_links[0]);


const hams = ProjectItem => {
  // Create elements needed to build a card
  const ulp = document.createElement("ul");
  const lip = document.createElement("li");
  const ap = document.createElement("a");
  // Append newly created elements into the DOM
 xp = document.getElementById("project_container");
xp.append(ulp);
  
  ulp.appendChild(lip);
  lip.appendChild(ap);

  // Set content and attributes
  //a.setAttribute("target", "blank");
  ap.innerHTML = ProjectItem.description;
  ap.setAttribute("href", ProjectItem.link);
  ulp.setAttribute("class", "proj");
};


project_links.forEach(ProjectItem => hams(ProjectItem));

