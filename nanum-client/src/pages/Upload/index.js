import { useEffect, useState } from "react";
import Header from "../../components/Header";
import { createBoard } from "../../api/boardAPI";
import Footer from "../../components/Footer";
import { Container } from "./style";
import UploadFileIcon from "@mui/icons-material/UploadFile";
import api from "../../api/baseAPI";
import { useNavigate } from "react-router-dom";

const Upload = () => {
  const navigate = useNavigate();
  const auth = () => {
    if (document.cookie.split("=")[1] === undefined) {
      navigate("/signin");
    }
  };

  const [itemInput, setItemInput] = useState({
    title: "",
    content: "",
    file: null,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setItemInput({
      ...itemInput,
      [name]: value,
    });
  };

  const handleFileInput = (e) => {
    const file = e.target.files[0];
    setItemInput({
      ...itemInput,
      file: file,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();

    formData.append("title", itemInput.title);
    formData.append("content", itemInput.content);
    formData.append("file", itemInput.file);

    api.defaults.headers.common["Authorization"] = `${
      document.cookie.split("=")[1]
    }`;

    try {
      const response = await createBoard(formData);
      console.log(response);
      alert("등록 완료!");
      navigate("/uploadlist");
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    auth();
  }, []);

  return (
    <>
      <Header />
      <Container>
        <form onSubmit={handleSubmit}>
          <div className="wrapper-in-form">
            <div className="input-wrapper">
              <input
                name="title"
                onChange={handleChange}
                placeholder="상품명"
              />
            </div>
            <div className="input-wrapper">
              <textarea
                name="content"
                onChange={handleChange}
                placeholder="설명:&#13;&#10;나눔 시간:&#13;&#10;나눔 장소: "
              />
            </div>
            <div className="input-wrapper file-input">
              <label className="input-file-button" htmlFor="file">
                <UploadFileIcon />
              </label>
              <input
                id="file"
                name="file"
                type="file"
                multiple
                accept="image/jpg,impge/png,image/jpeg,image/gif"
                onChange={handleFileInput}
              ></input>
            </div>
            <div className="btn-wrapper">
              <button className="upload-btn" type="submit">
                등록
              </button>
            </div>
          </div>
        </form>
      </Container>
      <Footer />
    </>
  );
};
export default Upload;
