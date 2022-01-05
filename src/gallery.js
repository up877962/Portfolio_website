////////CONFIGURATION FILE FOR THE GALLERY PAGE. ADD NEW CONTENT HERE TO DYNAMICALLY UPDATE PAGES/////////////////////


const gallery_data = [

{

  id: 1,
  pic:'./images/newlifecode2.png',
  description: "An image of some shit i did while at uni"

},
{

 id: 2,
 pic:'./images/2nderror.png',
 description: "some more shit i did at uni"


},
{
  id: 3,
  pic:"./images/adder.jpg",
  description: "An 8 bit adder developed with a popular logic gate simulator "


},
{
  id: 4,
  pic:"./images/poster.jpg",
  description: "a poster depicting a von neumantypo machine "


}

] 








const sectionBuilder = galleryItem => {
  // Create elements needed to build a card
  const picture = document.createElement("picture");
  const h4 = document.createElement("h4");
  const img = document.createElement("img");
  // Append newly created elements into the DOM
  const body = document.getElementById("gallery_container");
  body.append(picture);
  
  picture.append(img);
  picture.append(h4);

  // Set content and attributes

  h4.innerHTML = galleryItem.description
  img.setAttribute("src", galleryItem.pic);
  picture.setAttribute("class", "pics");
};

gallery_data.forEach(galleryItem => sectionBuilder(galleryItem));


