import { uploadFile } from "../services/upload-service";

const FileUpload = () => {

    const onSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData();
        const files = document.getElementById("excelFile");
        const option = document.getElementById("option").value;

        formData.append("excelFile", files.files[0]);
        uploadFile(option, formData);
    }


    return (
      <div>
        <form method="post" encType="multipart/form-data">
          <label> File Upload:  </label>
          <input type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
                 name="excelFile" id="excelFile"></input> 
          <select id="option">
            <option value="category"> 카테고리 </option>
            <option value="standard-products"> 기준상품 </option>
            <option value="partners"> 협력사 </option>
            <option value="partner-products"> 협력사상품 </option>
          </select>
          <button style={{marginLeft: "1rem"}} type="submit" onClick={(event) => onSubmit(event)}> 업로드 </button>
        </form> 
      </div>
    )
}

export default FileUpload;
  