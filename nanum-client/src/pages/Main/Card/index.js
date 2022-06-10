import TinderCard from "react-tinder-card";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import { useEffect, useState } from "react";
import { createHeart } from "../../../api/heartAPI";
import api from "../../../api/baseAPI";
import { getComment } from "../../../api/commentAPI";

const Card = ({ props }) => {
  const item = props;

  const [liked, setLiked] = useState(false);
  const [id, setId] = useState(0);
  const [commentCount, setCommentCount] = useState(0);
  const handleLike = async (boardId) => {
    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;

    const response = await createHeart(boardId);
    console.log(response);
    setLiked(response.data);
  };

  const fetch = async (boardId) => {
    const response = await getComment(boardId);
    setCommentCount(response.data.length);
  };

  useEffect(() => {
    setId(item.id);
    if (id !== 0) {
      fetch(id);
    }
  }, [id]);

  return (
    <TinderCard className="swipe">
      <div className="card">
        <div
          style={{ backgroundImage: `url(${item.imgUrl})` }}
          className="item-image"
        ></div>
        <div className="item-info">
          <div className="description">
            <h2>{item.title}</h2>
            <br />
            <h3>{item.content}</h3>
          </div>
        </div>
        <div className="sub-info">
          <div className="comment-count">
            <div>{commentCount}</div>
            <div>Comments</div>
          </div>
          <div
            className="like-btn"
            onClick={() => {
              handleLike(item.id);
            }}
            onTouchEnd={() => {
              handleLike(item.id);
            }}
          >
            {item.id === 9999 ? (
              <></>
            ) : !liked ? (
              <FavoriteBorderIcon
                sx={{ fontSize: 40 }}
                style={{ color: "aliceblue" }}
              />
            ) : (
              <FavoriteIcon color="error" sx={{ fontSize: 40 }} />
            )}
          </div>
        </div>
      </div>
    </TinderCard>
  );
};

export default Card;
