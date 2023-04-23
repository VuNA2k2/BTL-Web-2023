let slideIndex = 0;
      const slides = document.querySelectorAll('.slideshow img');
      const prevBtn = document.querySelector('.slideshow button.prev');
      const nextBtn = document.querySelector('.slideshow button.next');

      function showSlide(index) {
        if (index < 0) {
          index = slides.length - 1;
        } else if (index >= slides.length) {
          index = 0;
        }
        slides.forEach(slide => slide.classList.remove('active'));
        slides[index].classList.add('active');
        slideIndex = index;
      }

      function prevSlide() {
        showSlide(slideIndex - 1);
      }

      function nextSlide() {
        showSlide(slideIndex + 1);
      }
