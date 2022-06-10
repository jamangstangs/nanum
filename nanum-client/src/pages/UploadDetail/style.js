import styled from "@emotion/styled";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  gap: 20px;

  .item-details {
    width: 370px;
    box-sizing: border-box;
    position: relative;
    perspective: 1110px;

    .item-image {
      width: 370px;
      height: 180px;
      border-radius: 10px;
      transition: transform 0.5s, box-shadow 0.3s;
      backface-visibility: hidden;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
    }

    .item-image.front {
      position: absolute;
      box-sizing: border-box;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;

      transform: rotateY(0deg);
    }

    .item-image.back {
      padding: 20px;
      box-sizing: border-box;
      transform: rotateY(-180deg);

      .item-content {
        height: calc(100% - 50px);
      }
    }
  }

  .item-details:hover .front {
    transform: rotateY(180deg);
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  }

  .item-details:hover .back {
    transform: rotateY(0deg);
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  }
  .comment-list {
    overflow: scroll;
    width: 100vw;
    height: 50vh;
    padding: 20px;
    box-sizing: border-box;
    .comment {
      display: flex;
      align-items: center;
      padding: 10px;

      .comment-text {
        margin-left: 10px;
        background-color: lightgray;
        padding: 15px;
        border-radius: 20px;
      }
    }
  }

  .complete-btn {
    padding: 5px;
    position: absolute;
    bottom: 65px;
    left: 0;
    right: 0;
    margin: auto;
    width: 80vw;

    > button {
      width: 100%;
      height: 30px;

      background-color: #4eabd2;
      border: none;
      border-radius: 5px;
      font-weight: bold;
      color: aliceblue;
    }
  }
`;

export { Container };
