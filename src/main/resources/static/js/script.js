let selectRoles = () => {
    if (document.querySelector(".list__role").style.display === "none" || document.querySelector(".list__role").style.display === "") {
        document.querySelector(".list__role").style.display = "block";
    }
    else {
        document.querySelector(".list__role").style.display = "none";
    }
}