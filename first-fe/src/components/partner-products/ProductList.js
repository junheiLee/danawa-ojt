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
                <th style={{width: "5rem"}}>대분류명</th>
                <th style={{width: "5.5rem"}}>협력사코드</th>
                <th style={{width: "4rem"}}>상품코드</th>
                <th style={{width: "10rem"}}>상품명</th>
                <th style={{width: "5rem"}}>PC가</th>
                <th style={{width: "5rem"}}>모바일가</th>
                <th style={{width: "3.7rem"}}>등록일</th>
                <th style={{width: "3.7rem"}}>url</th>
                <th style={{width: "3.7rem"}}>이미지</th>
              </tr>
            </thead>
            <tbody>
              {
                props.products.map((product) => (
                  <tr key={product.partnerCode + "" + product.code}>
                      <td style={{width: "5rem"}}> {product.categoryName} </td>
                      <td style={{width: "5.5rem"}}> {product.partnerCode} </td>
                      <td style={{width: "4rem"}}> {product.code} </td>
                      <td style={{width: "10rem"}}> {product.name} </td>
                      <td style={{width: "5rem"}}> {product.pcPrice} </td>
                      <td style={{width: "5rem"}}> {product.mobilePrice} </td>
                      <td style={{width: "3.7rem"}}> {product.createdAt} </td>
                      <td style={{width: "3.7rem"}}> <a href={product.url}>url</a></td>
                      <td style={{width: "3.7rem"}}> <a href={product.imageUrl}>이미지</a></td>
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
  