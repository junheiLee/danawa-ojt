import './Main.css';
import {useState, useEffect} from "react";
import StandardProducts from "./StandardProducts";
import PartnerProducts from "./PartnerProducts";
import FileUpload from '../components/FileUpload';
import axios from "axios";

const Main = () => {

  const [categories, setCategories] = useState([]);

  useEffect(() =>{
      getCategories();
  }, [])

  const getCategories = async() => {
      try{
          const response = axios.get(`/category`, {
              headers: {
                  "Content-Type": "application/json"
              }
          })

          setCategories((await response).data.categories);

      } catch(error) {

      }
  }

  return (
    <>
        <div className='File-Upload'>
            <FileUpload />
        </div>
        <div className="Left-Layer">
            <StandardProducts categories={categories} />
        </div>
       
        <div className="Right-Layer">
            <PartnerProducts categories={categories} />
        </div>
    </>
  )
}
export default Main;
