import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Container } from "./style";
import { useEffect, useState } from "react";
import { completeBoard, getDetailBoard } from "../../api/boardAPI";
import { useNavigate, useParams } from "react-router-dom";
import { getComment } from "../../api/commentAPI";
import api from "../../api/baseAPI";
import { Avatar } from "@mui/material";

const UploadDetail = () => {
  const { param } = useParams();

  const [item, setItem] = useState({});
  const [commentList, setCommentList] = useState([]);

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

    const response = await getDetailBoard(param);
    setItem(response.data);
    console.log(response);
    const comments = await getComment(param);
    setCommentList(comments.data);
  };

  const handleComplete = async () => {
    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;

    const response = await completeBoard(param);
    alert("상품 나눔 완료!");
    console.log(response);
  };

  useEffect(() => {
    auth();
    fetch();
  }, []);

  return (
    <>
      <Header />
      <Container>
        <div className="item-details">
          <div
            className="item-image front"
            style={{ backgroundImage: `url(${item.imgUrl})` }}
          ></div>
          <div className="item-image back">
            <div className="item-title">
              <h2>{item.title}</h2>
            </div>
            <p className="item-content">{item.content}</p>
            <div className="item-contact">{item.userName}</div>
          </div>
        </div>
        <div className="comment-list">
          {commentList &&
            commentList.map((comment, idx) => (
              <div className="comment" key={comment.id}>
                <Avatar className="comment-image" alt={`${comment.id}`} />
                <p className="comment-text"> {comment.comment} </p>
              </div>
            ))}
        </div>
        <div className="complete-btn">
          <button onClick={handleComplete}>나눔 완료</button>
        </div>
      </Container>
      <Footer />
    </>
  );
};
export default UploadDetail;
