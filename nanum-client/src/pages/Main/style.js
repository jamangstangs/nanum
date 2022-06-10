import styled from "@emotion/styled";
import { height } from "@mui/system";

const Container = styled.div`
  .swipe {
    position: absolute;
    width: 100vw;
    height: calc(100% - 30px);
    display: flex;
    .card {
      position: relative;
      left: 0;
      right: 0;
      margin: auto;

      width: 600px;
      max-width: 85vw;
      height: 600px;

      box-sizing: border-box;

      border-radius: 20px;

      background-size: cover;
      background-position: center;
      box-shadow: 0px 3px 25px 18px rgba(0, 0, 0, 0.05);

      .item-image {
        height: 400px;
        border-radius: 20px 20px 0px 0px;
        background-size: cover;
        background-position: center;
      }
      .item-info {
        padding: 20px;
        border-radius: 0px 0px 20px 20px;
        height: 150px;
        background-color: #ffffff;
      }

      .sub-info {
        display: flex;
        height: 80px;
        background-color: #4eabd2;
        border-radius: 0 0 20px 20px;
        justify-content: space-between;
        .comment-count {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          flex: 2;
          border-right: 2px solid aliceblue;
          color: aliceblue;

          > div:nth-of-type(1) {
            font-size: 30px;
            font-weight: bold;
          }
        }
        .like-btn {
          flex: 1;
          display: flex;
          justify-content: center;
          align-items: center;
          width: 50px;

          &:hover {
            cursor: pointer;
          }
        }
      }
    }
  }
`;

export { Container };
