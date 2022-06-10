import { Link } from "react-router-dom";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import { useState } from "react";
import { createHeart } from "../../../api/heartAPI";
import api from "../../../api/baseAPI";

const Item = ({ props }) => {
  const item = props;

  const [liked, setLiked] = useState(true);

  const handleLike = async (boardId) => {
    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;

    const response = await createHeart(boardId);
    console.log(response.data);
    setLiked(response.data);
  };

  return (
    <div className="item-wrapper">
      <Link to={`/likelist/${item.boardId}`}>
        <div
          className="item"
          style={{ backgroundImage: `url(${item.imgUrl})` }}
        ></div>
      </Link>
      <div
        className="like-btn"
        onClick={() => {
          handleLike(item.boardId);
        }}
      >
        {!liked ? (
          <FavoriteBorderIcon fontSize="large" />
        ) : (
          <FavoriteIcon fontSize="large" color="error" />
        )}
      </div>
    </div>
  );
};

export default Item;
