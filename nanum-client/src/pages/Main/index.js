import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Container } from "./style";
import { useEffect, useState } from "react";
import { getBoard } from "../../api/boardAPI";
import api from "../../api/baseAPI";
import Card from "./Card";
import { useNavigate } from "react-router-dom";

const Main = () => {
  const [items, setItems] = useState([]);
  const noneItem = {
    id: 9999,
    imgUrl:
      "https://mblogthumb-phinf.pstatic.net/MjAyMDA1MTJfMjY2/MDAxNTg5MjE1ODQxNTM2.pofJNbrr6CkeEHAGCy7KxHzBHTNzL_XOTzJVskX0BIcg.fSwlOO3M4gvjiKuTGpv48KGKGXU7WcNdaLjTfddjP84g.JPEG.z12wow/AA5837D1-1827-476A-AE47-8D505AEE4C08-14234-000004149E52D831_file.jpg?type=w800",
    title: "",
    content: "",
    userId: "없음!",
  };
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
    const response = await getBoard();
    console.log(response.data);
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
        {items.length > 0 ? (
          items.map((item) => <Card props={item} key={item.id} />)
        ) : (
          <Card props={noneItem} key={noneItem.id} />
        )}
      </Container>
      <Footer />
    </>
  );
};
export default Main;
