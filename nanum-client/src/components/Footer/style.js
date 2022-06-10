import styled from "@emotion/styled";

const Container = styled.div`
  bottom: 0;
  width: 100%;
  position: fixed;
  height: 50px;

  border-top: 1px solid #f9f8f8;

  box-shadow: 0px -26px 35px rgba(0, 0, 0, 0.04);
  background-color: white;
  .menu {
    height: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 25px;

    a {
      button {
        width: 48px;
        padding: 8px;
        svg {
          width: 100%;
          height: 100%;
        }
      }

      :nth-of-type(2) {
        transform: rotate(180deg);
      }
    }
  }
`;

export { Container };
