/*document.addEventListener('DOMContentLoaded', (event) => {
    const images = ['1.jpg', '2.jpg', '3.jpg', '4.jpg', '5.jpg','6.jpg', '7.jpg', '8.jpg', '9.jpg', '10.jpg','11.jpg', '12.jpg', '13.jpg', '14.jpg', '15.jpg','16.jpg'];
    const randomIndex = Math.floor(Math.random() * images.length);
    const selectedImage = images[randomIndex];
    document.body.style.backgroundImage = `url('./admin/custom/imgs/bgimgs/${selectedImage}')`;
});
*/



  document.addEventListener('DOMContentLoaded', function() {
    function setBackgroundImage() {
      const images = ['1.jpg', '2.jpg', '3.jpg', '4.jpg', '5.jpg', '6.jpg', '7.jpg', '8.jpg', '9.jpg'];
      const randomIndex = Math.floor(Math.random() * images.length);
      const selectedImage = images[randomIndex];
      document.body.style.backgroundImage = `url('./admin/custom/imgs/bgimgs/${selectedImage}')`;
    }
    setBackgroundImage();
  });
