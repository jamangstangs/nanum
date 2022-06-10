import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Container } from "./style";
import { useEffect, useState } from "react";
import { getMyBoard } from "../../api/boardAPI";
import api from "../../api/baseAPI";
import { Link, useNavigate } from "react-router-dom";

const UploadList = () => {
  const [items, setItems] = useState([]);
  const navigate = useNavigate();
  const auth = () => {
    if (document.cookie.split("=")[1] === undefined) {
      navigate("/signin");
    }
  };
  const fetch = async () => {
    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;

    const response = await getMyBoard();
    setItems(response.data);
  };

  useEffect(() => {
    auth();
    fetch();
  }, []);

  return (
    <>
      <Header />
      <Container>
        <div className="menu-name">
          <h2>나의 나눔 목록</h2>
        </div>
        <div className="gird-container">
          {items &&
            items.map((item) => (
              <Link key={item.id} to={`/uploadlist/${item.id}`}>
                <div
                  className="item"
                  style={{ backgroundImage: `url(${item.imgUrl})` }}
                ></div>
              </Link>
            ))}
          <div className="item none"></div>
          <div className="item none"></div>
          <div className="item none"></div>
          <div className="item none"></div>
        </div>
      </Container>
      <Footer />
    </>
  );
};
export default UploadList;
