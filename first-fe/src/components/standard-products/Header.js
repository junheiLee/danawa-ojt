import '../Header.css'
import { download } from '../../services/standard-product-service'

const Header = (props) => {

    const handleClick = (e) => {
        download(props.page);
    }

    return (
     <>
        <div style={{textAlign:'left', marginTop: "0.5rem"}}>
            <h3>기준상품</h3>
        </div>
        <div style={{textAlign:'left'}}>
            <select name="category" onChange={(e) => props.setCategory(e.target.value)}>
                <option>카테고리 선택</option>
            {
                props.categories.map(category => (
                    <option key={category.code} value={category.name}>{category.name}</option>
                ))
            }
            </select>

            <label style={{marginLeft:'5.2rem'}}>코드: </label>
                <input type="text"
                    id="code"></input>
                <label>이름: </label>
                <input type="text"
                    id="name"></input>
                <button
                    onClick={() => {
                        props.setSearchCode(document.getElementById("code").value);
                        props.setSearchName(document.getElementById("name").value);
                    }}
                >검색</button>

        </div>
        <div className="Layer-Header">
        <div style={{marginTop: "0.3rem"}}>
                <input style={{marginBottom: "1rem"}} 
                        type="button" value="다운로드"
                        onClick={e => handleClick(e)}/>
            </div>
        </div>
      </>
    )
}
export default Header;
  