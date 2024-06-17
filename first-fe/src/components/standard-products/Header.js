import '../../pages/Header.css'
import { download } from '../../services/standard-product-service'

const Header = () => {

    const handleClick = (e) => {
        download();
    }

    return (
      <div className="Layer-Header">
          <input style={{margin: "1rem"}} 
                 type="button" value="다운로드"
                 onClick={e => handleClick(e)}/>
      </div>
    )
}
export default Header;
  