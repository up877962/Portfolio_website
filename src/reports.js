////////CONFIGURATION FILE FOR THE REPORTS PAGE. ADD NEW CONTENT HERE TO DYNAMICALLY UPDATE PAGES/////////////////////



const links = [

{
id: 1,
link: "./Resources/UP877962.pdf" ,
description: "The use of insecure IoT devices in the creation and propagation of criminal distributed systems( botnets) and the implications of these bots on their local network"

},

{
id: 2,
link: "./Resources/up877962_pp.pdf" ,
description: "A report that documents experimentation with parallel programming"

}



 
 ];



console.log(links[0]);


const blag = ReportItem => {
  // Create elements needed to build a card
  const ul = document.createElement("ul");
  const li = document.createElement("li");
  const a = document.createElement("a");
  // Append newly created elements into the DOM
 x = document.getElementById("reports");
x.append(ul);
  
  ul.appendChild(li);
  li.appendChild(a);

  // Set content and attributes
  //a.setAttribute("target", "blank");
  a.innerHTML = ReportItem.description;
  a.setAttribute("href", ReportItem.link);
  ul.setAttribute("class", "pdfs");
};


links.forEach(ReportItem => blag(ReportItem));





