import '../List.css'
import {useEffect} from "react";

const ProductList = (props) => {

  useEffect(() => {
    console.log(props.products)
  }, [props.products])

  return (
    <>
      <div className='container'>
          <table>
            <thead>
              <tr>
                <th style={{minWidth: "5rem"}}>대분류명</th>
                <th style={{minWidth: "5rem"}}>상품코드</th>
                <th style={{minWidth: "4rem"}}>상품명</th>
                <th style={{minWidth: "5rem"}}>묶음조건</th>
                <th style={{minWidth: "12rem"}}>설명</th>
                <th style={{minWidth: "3.7rem"}}>최저가</th>
                <th style={{minWidth: "3.7rem"}}>평균가</th>
                <th style={{minWidth: "3.7rem"}}>업체수</th>
              </tr>
            </thead>
            <tbody>
              {
                props.products.map(product => (
                  <tr key={product.code + product.bundleCondition}>
                      <td > {product.categoryName} </td>
                      <td>{ product.code} </td>
                      <td> {product.name} </td>
                      <td> {product.bundleCondition} </td>
                      <td> {product.description} </td>
                      <td> {product.lowestPrice} </td>
                      <td> {product.averagePrice} </td>
                      <td> {product.partnerCount} </td>
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
  