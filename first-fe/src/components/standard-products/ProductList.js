import '../List.css'
import {useEffect} from "react";

const ProductList = (props) => {

  useEffect(() => {
    
  }, [props.products])

  return (
    <>
      <div className='container'>
          <table>
            <thead>
              <tr>
                <th style={{width: "5rem"}}>대분류명</th>
                <th style={{width: "5rem"}}>상품코드</th>
                <th style={{width: "4rem"}}>상품명</th>
                <th style={{width: "5rem"}}>묶음조건</th>
                <th style={{width: "12rem"}}>설명</th>
                <th style={{width: "3.7rem"}}>최저가</th>
                <th style={{width: "3.7rem"}}>평균가</th>
                <th style={{width: "3.7rem"}}>업체수</th>
              </tr>
            </thead>
            <tbody>
              {
                props.products.map(product => (
                  <tr key={product.code + product.bundleCondition}>
                      <td style={{width: "5rem"}}> {product.categoryName} </td>
                      <td style={{width: "5rem"}}> {product.code} </td>
                      <td style={{width: "4rem"}}> {product.name} </td>
                      <td style={{width: "5rem"}}> {product.bundleCondition} </td>
                      <td style={{width: "12rem"}}> {product.description} </td>
                      <td style={{width: "3.7rem"}}> {product.lowestPrice} </td>
                      <td style={{width: "3.7rem"}}> {product.averagePrice} </td>
                      <td style={{width: "3.7rem"}}> {product.partnerCount} </td>
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
  