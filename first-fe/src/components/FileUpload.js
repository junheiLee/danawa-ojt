import { uploadFile } from "../services/upload-service";
import {useState} from "react";
import Loading from './Loading';

const FileUpload = () => {
    const [loading, setLoading] = useState(false);

    const handleSubmit = async(e) => {

        e.preventDefault();
        const excelFile = document.getElementById("excelFile");
        const option = document.getElementById("option").value;

        const formData = new FormData();
        formData.append("excelFile", excelFile.files[0]);
        
        uploadFile(option, formData, setLoading);
    }

    return (
      <div>
        {loading? <Loading /> : null}
        <form onSubmit={ e => handleSubmit(e) }>
          <label> File Upload:  </label>
          <input type="file" 
                 required accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
                 name="excelFile" id="excelFile"></input> 
          <select id="option">
            <option value="category"> 카테고리 </option>
            <option value="standard-products"> 기준상품 </option>
            <option value="partners"> 협력사 </option>
            <option value="partner-products"> 협력사상품 </option>
          </select>
          <input style={{marginLeft: "1rem"}} type="submit" value="업로드" />
        </form> 
      </div>
    )
}

export default FileUpload;
  