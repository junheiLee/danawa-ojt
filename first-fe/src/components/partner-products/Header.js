import '../Header.css'
import { download } from '../../services/partner-product-service'

const Header = (props) => {

    const downloadClick = (e) => {
        download(props.page, props.category, props.searchCode, props.searchName);
    }

    const setSearch = (e) => {
        e.preventDefault();

        let code = document.querySelector(".code").value;
        let name = document.querySelector(".name").value;
        props.setSearchCode(code);
        props.setSearchName(name);
    }

    return (
        <>
        <div style={{textAlign:'left', marginTop: "0.5rem"}}>
            <h3>협력사 상품</h3>
        </div>
        <div style={{textAlign:'left'}}>

            <select name="category" onChange={(e) => {
                                                        props.setCategory(e.target.value);
                                                        props.setPage(1)}}>
                <option>카테고리 선택</option>
            {
                props.categories.map(category => (
                    <option key={category.code} value={category.code}>{category.name}</option>
                ))
            }
            </select>


            <label style={{marginLeft:'17rem'}}>코드: </label>
            <input type="text" className="code"></input>
            <label>이름: </label>
            <input type="text" className='name'></input>
            <input type="button" value="검색" onClick={e => setSearch(e)}></input>

        </div>
        <div className="Layer-Header">
            
            <div style={{marginTop: "0.3rem"}}>
                <input style={{marginBottom: "1rem"}} 
                        type="button" value="다운로드"
                        onClick={e => downloadClick(e)}/>
            </div>

        </div>
      </>
    )
}
export default Header;