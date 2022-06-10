import styled from "@emotion/styled";

const Container = styled.div`
  .menu-name {
    position: absolute;
    top: 100px;
    width: 135px;
    left: 0;
    right: 0;
    margin: 0 auto;
  }
  .gird-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 180px;
    overflow-y: auto;

    position: absolute;
    width: 100vw;
    height: 100%;
    top: 160px;

    padding: 0 10px;
    box-sizing: border-box;
    gap: 10px;

    .item {
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);

      height: 180px;
      border-radius: 10px;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
    }

    .none {
      box-shadow: none;
      border: transparent;
      background-color: transparent;
    }
  }
`;

export { Container };
