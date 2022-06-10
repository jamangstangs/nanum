import React from "react";
import "./Header.css";
import logo from "../../img/nanum_logo.png";

const Header = () => {
  return (
    <div className="header">
      <a href="/">
        <img className="header__logo" src={logo} alt="nanum logo" />{" "}
      </a>
    </div>
  );
};

export default Header;
