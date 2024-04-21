/* SHOW MENU */
const showMenu = (toggleId, navId) => {
    const toggle  = document.getElementById(toggleId);
    const nav     = document.getElementById(navId);

    if (toggle && nav) {
        toggle.addEventListener("click", () => {
            nav.classList.toggle("show-menu");
        });
    }
}
showMenu("nav-toggle", "nav-menu");

/* REMOVE MOBILE MENU */
const navLink = document.querySelectorAll(".nav__link");

const linkAction = () => {
    const navMenu = document.getElementById("nav-menu");
    navMenu.classList.remove("show-menu");
}
navLink.forEach((element) => element.addEventListener("click", linkAction));

/* SCROLL SECTIONS ACTIVE LINK */
const sections = document.querySelectorAll("section[id]");

const scrollActive = () => {
    const scrollY = window.scrollY;

    sections.forEach((element) => {
        const sectionHeight = element.offsetHeight;
        const sectionTop = element.offsetTop - 50;
        const sectionId = element.getAttribute("id");

        if (scrollY > sectionTop && scrollY <= sectionTop + sectionHeight) {
            document.querySelector(".nav__menu").classList.add('active-link')
        } else {
            document.querySelector(".nav__menu").classList.remove('active-link')
        }
    });
}
window.addEventListener("scroll", scrollActive);

/* SHOW SCROLL TOP */
const scrollTop = () => {
    const scrollTop = document.getElementById('scroll-top');
    if (this.scrollY >= 200)
        scrollTop.classList.add('show-scroll');
    else
        scrollTop.classList.remove('show-scroll');
}
window.addEventListener('scroll', scrollTop);

/* THEME */
const themeButton = document.getElementById("theme-changer");
const darkTheme = 'dark-theme';
const iconDark = 'bx-sun';

const selectedTheme = localStorage.getItem('selected-theme');
const selectedIcon = localStorage.getItem('selected-icon');

const getCurrentTheme = () => {
    return document.getElementById("theme").classList.contains(darkTheme) ? 'dark' : 'light'
}
const getCurrentIcon = () => {
    return document.getElementById("theme").classList.contains(iconDark) ? 'bx-moon' : 'bx-sun'
}

if (selectedTheme) {
    document.getElementById("theme").classList[selectedTheme === 'dark' ? 'add' : 'remove'](darkTheme);
    themeButton.classList[selectedIcon === 'bx-moon' ? 'add' : 'remove'](iconDark);
}

themeButton.addEventListener('click', () => {
    document.getElementById("theme").classList.toggle(darkTheme);
    themeButton.classList.toggle(iconDark);

    localStorage.setItem('selected-theme', getCurrentTheme());
    localStorage.setItem('selected-icon', getCurrentIcon());
})