import './Pagination.css'
import Header from "../components/partner-products/Header"
import ProductList from "../components/partner-products/ProductList"
import Pagination from "react-js-pagination"
import {useEffect, useState} from "react";
import axios from 'axios';

const PartnerProducts = () => {

    const [page, setPage] = useState(1);
    const[itemCount, setItemCount] = useState(0);
    
    const getPageInfo = async() => {
        const response = await axios.get(`/partner-products/page-info`, {
            headers: {
                "Content-Type": "application/json"
            }
        })
        setItemCount(response.data.totalItem);
    }

    const handlePageChange = (page) => {
        setPage(page);
    }

    useEffect(()=>{
        getPageInfo();
    }, [])

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
                    totalItemsCount={itemCount}
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
export default PartnerProducts;
  