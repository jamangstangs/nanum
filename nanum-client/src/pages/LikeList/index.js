import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Container } from "./style";
import { useEffect, useState } from "react";
import { getHeartBoard } from "../../api/boardAPI";
import api from "../../api/baseAPI";
import { useNavigate } from "react-router-dom";

import Item from "./Item";

const LikeList = () => {
  const navigate = useNavigate();
  const auth = () => {
    if (document.cookie.split("=")[1] === undefined) {
      navigate("/signin");
    }
  };

  const [items, setItems] = useState([]);
  const fetch = async () => {
    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;
    const response = await getHeartBoard();
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
          <h2>좋아요 목록</h2>
        </div>
        <div className="gird-container">
          {items &&
            items.map((item) => <Item props={item} key={item.boardId} />)}
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
export default LikeList;
