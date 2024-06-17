import '../Header.css'
import { download } from '../../services/standard-product-service'

const Header = (props) => {

    const handleClick = (e) => {
        download(props.page);
    }

    return (
     <>
        <div style={{textAlign:'left', marginTop: "1rem"}}>
            <h3>기준상품</h3>
        </div>
        <div className="Layer-Header">
            <input style={{marginBottom: "1rem"}} 
                    type="button" value="다운로드"
                    onClick={e => handleClick(e)}/>
        </div>
      </>
    )
}
export default Header;
  