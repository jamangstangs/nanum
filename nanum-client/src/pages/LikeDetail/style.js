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

  .comment-form {
    padding: 5px;
    box-sizing: border-box;
    position: absolute;
    bottom: 50px;
    width: 100%;
    border-top: 1px solid lightgray;

    .wrapper-in-form {
      display: flex;
      justify-content: center;
      align-items: center;
      .comment-input {
        padding: 10px;
        max-height: 34px;
        box-sizing: border-box;
        height: auto;
        width: 70vw;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
      }
      .comment-input-btn {
        border: none;
        border-radius: 5px;
        height: 34px;
        width: 60px;
        margin-left: 10px;
        background-color: #4eabd2;
        font-weight: bolder;
        color: aliceblue;
      }
    }
  }
`;

export { Container };
