import '../List.css'
import {useState, useEffect} from "react";
import axios from "axios";


const ProductList = (props) => {

  useEffect(() => {
    alert("머야")
  }, [props.products])

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
                <th style={{minWidth: "3.7rem"}}>이미지</th>
              </tr>
            </thead>
            <tbody>
              {
                props.products.map((product) => (
                  <tr key={product.partnerCode + "" + product.code}>
                      <td> {product.categoryName} </td>
                      <td> {product.partnerCode} </td>
                      <td> {product.code} </td>
                      <td> {product.name} </td>
                      <td> {product.pcPrice} </td>
                      <td> {product.mobilePrice} </td>
                      <td> {product.createdAt} </td>
                      <td> <a href={product.url}>url</a></td>
                      <td> <a href={product.imageUrl}>이미지</a></td>
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
  