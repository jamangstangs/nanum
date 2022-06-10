import styled from "@emotion/styled";

const Container = styled.div`
  position: absolute;
  width: 100vw;
  height: calc(100% - 30px);
  margin: auto;
  align-items: center;
  justify-content: center;
  display: flex;

  form {
    position: relative;
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
        input,
        textarea,
        label {
          width: 100%;
          padding: 0 10px;
          height: 34px;
          border: 1px solid #ccc;
          border-radius: 5px;
          box-sizing: border-box;
        }

        label {
          display: block;
          height: 200px;
          position: relative;

          svg {
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            width: 100px;
            height: 100px;
            margin: auto;
            position: absolute;
            color: #ccc;
          }
        }

        textarea {
          padding: 10px;

          height: 80px;
        }
      }
      .file-input {
        #file {
          display: none;
        }
      }

      .btn-wrapper {
        .upload-btn {
          width: 100%;
          height: 30px;
          border-radius: 5px;
        }
      }

      button {
        background-color: #4eabd2;
        border-radius: 5px;
        border: none;
        font-weight: bold;
        color: aliceblue;
      }
    }
  }
`;

export { Container };
