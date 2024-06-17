import './Pagination.css'
import Header from "../components/standard-products/Header"
import ProductList from "../components/standard-products/ProductList"
import Pagination from "react-js-pagination"
import {useEffect, useState} from "react";

const StandardProducts = () => {

    const [page, setPage] = useState(1);
    const[totalPage, setTotalPage] = useState(5);
    const[itemCount, setItemCount] = useState(100);
    
    const handlePageChange = (page) => {
        setPage(page);
    }

    useEffect(()=>{

    }, [page])

    return (
        <>
            <div>
                <Header page={page}/>
            </div>
            <div>
                <ProductList page={page}/>
            </div>
            <div>
                <Pagination 
                    activePage={page}
                    itemsCountPerPage={30}
                    totalItemsCount={itemCount}
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