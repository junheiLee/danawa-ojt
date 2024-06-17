import '../List.css'
import {useState, useEffect} from "react";
import axios from "axios";


const ProductList = (props) => {

  const [products, setProducts] = useState([]);
  const [idx, setIdx] = useState(1);
  
  useEffect (() => {
    getProducts();
  }, [props.page])

  const getProducts = async() => {

    try {
      const response =  axios.get(`/partner-products?page=${props.page}`, {
          headers: {
              "Content-Type": "applicstion/json"
          }
      })

      setProducts((await response).data.products);

    } catch (error) {
  
    }
  }

  return (
    <>
      <div className='container'>
          <table>
            <thead>
              <tr>
                <th style={{minWidth: "5rem"}}>대분류명</th>
                <th style={{minWidth: "5.5rem"}}>협력사코드</th>
                <th style={{minWidth: "5rem"}}>상품코드</th>
                <th style={{minWidth: "5rem"}}>상품명</th>
                <th style={{minWidth: "5rem"}}>PC가</th>
                <th style={{minWidth: "5rem"}}>모바일가</th>
                <th style={{minWidth: "3.7rem"}}>등록일</th>
                <th style={{minWidth: "3.7rem"}}>url</th>
                <th style={{minWidth: "3.7rem"}}>이미지url</th>
              </tr>
            </thead>
            <tbody>
              {
                products.map((product) => (
                  <tr key={product.code}>
                      <td> {product.categoryName} </td>
                      <td> {product.partnerCode} </td>
                      <td> {product.code} </td>
                      <td> {product.name} </td>
                      <td> {product.pcPrice} </td>
                      <td> {product.mobilePrice} </td>
                      <td> {product.createdAt} </td>
                      <td> {product.url} </td>
                      <td> {product.imageUrl} </td>
                  </tr>
                ))
              }
            </tbody>
          </table>
      </div>
    </>
    )
}
export default ProductList;
  