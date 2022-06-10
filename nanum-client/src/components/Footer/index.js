import { Container } from "./style";
import FavoriteIcon from "@mui/icons-material/Favorite";
import IconButton from "@material-ui/core/IconButton";
import DownloadForOfflineIcon from "@mui/icons-material/DownloadForOffline";
import PersonIcon from "@mui/icons-material/Person";
import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <Container className="footer">
      <div className="menu">
        <Link to="/likelist">
          <IconButton>
            <FavoriteIcon />
          </IconButton>
        </Link>
        <Link to="/upload">
          <IconButton>
            <DownloadForOfflineIcon />
          </IconButton>
        </Link>
        <Link to="/uploadlist">
          <IconButton>
            <PersonIcon className="header__icon" fontSize="large" />
          </IconButton>
        </Link>
      </div>
    </Container>
  );
};

export default Footer;
