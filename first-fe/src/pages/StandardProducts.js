import './Pagination.css'
import Header from "../components/standard-products/Header"
import ProductList from "../components/standard-products/ProductList"
import Pagination from "react-js-pagination"
import {useEffect, useState} from "react";
import axios from 'axios';

const StandardProducts = (props) => {

    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);

    const [category, setCategory] = useState();
    const[orderBy, setOrderBy] = useState("");
    const [searchName, setSearchName] = useState("");
    const [searchCode, setSearchCode] = useState("");

    const handlePageChange = (page) => {
        setPage(page);
    }

    useEffect(()=>{
        getProducts();
    }, [page, searchName, searchCode, category, orderBy])

    const getProducts = async() => {

        try {
          const response =  axios.get(`/standard-products?page=${page}&searchName=${searchName}&searchCode=${searchCode}`, {
              headers: {
                  "Content-Type": "applicstion/json"
              }
          })
    
          setProducts((await response).data.products);
    
        } catch (error) {
          alert(error.response.data.message);
        }
    }

    return (
        <>
            <div>
                <Header           
                    page={page}
                    setSearchName={input => setSearchName(input)}
                    setSearchCode={input => setSearchCode(input)}
                    setCategory={option => setCategory(option)}
                    setOrderBy={option => setOrderBy(option)}
                    categories={props.categories}
                />
            </div>
            <div>
                <ProductList products={products}/>
            </div>
            <div>
                <Pagination 
                    activePage={page}
                    totalItemsCount={10}
                    itemsCountPerPage={30}
                    pageRangeDisplayed={5}
                    prevPageText={"<"}
                    nextPageText={">"}
                    onChange={handlePageChange}
                />
            </div>
        </>
    )
}
export default StandardProducts;