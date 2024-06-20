import './Pagination.css'
import Header from "../components/standard-products/Header"
import ProductList from "../components/standard-products/ProductList"
import Pagination from "react-js-pagination"
import {useEffect, useState} from "react";
import axios from 'axios';

const StandardProducts = (props) => {

    const [products, setProducts] = useState([]);
    const [totalItemsCount, setTotalItemsCount] = useState(0);
    const [page, setPage] = useState(1);

    const [category, setCategory] = useState('');
    const [orderBy, setOrderBy] = useState('');

    const [searchName, setSearchName] = useState('');
    const [searchCode, setSearchCode] = useState('');

    const handlePageChange = (page) => {
        setPage(page);
    }

    useEffect(()=>{
        getProducts();
    }, [page, searchName, searchCode, category, orderBy])

    const getProducts = async() => {

        const url = decodeURI(`/standard-products?page=${page}&category=${category}&searchName=${searchName}&searchCode=${searchCode}`);

        try {
          const response =  axios.get(url, {
              headers: {
                  "Content-Type": "applicstion/json"
              }
          })
    
          setTotalItemsCount((await response).data.totalItemsCount);
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
                    setPage={page => setPage(page)}
                    searchName={searchName}
                    setSearchName={setSearchName}
                    searchCode={searchCode}
                    setSearchCode={setSearchCode}
                    category={category}
                    setCategory={option => setCategory(option)}
                    orderBy={orderBy}
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
                    totalItemsCount={totalItemsCount}
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