import styled from "@emotion/styled";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;

  .nanum-logo {
    margin: 100px auto;
    width: 240px;
  }

  form {
    .wrapper-in-form {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 10px;

      > div {
        width: 300px;
        text-align: center;
        font-size: 14px;
      }

      .input-wrapper {
        input {
          width: 100%;
          padding: 0 10px;
          height: 34px;
          border: 1px solid #ccc;
          border-radius: 5px;
          box-sizing: border-box;
        }
      }

      .btn-wrapper {
        .signin-btn {
          width: 100%;
          height: 30px;
          background-color: #4eabd2;
          border: none;
          border-radius: 5px;
          font-weight: bold;
          color: aliceblue;
        }
      }
    }
  }
`;

export { Container };
