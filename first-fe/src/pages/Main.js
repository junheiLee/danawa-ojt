import './Main.css';
import StandardProducts from "./StandardProducts";
import PartnerProducts from "./PartnerProducts";
import FileUpload from '../components/FileUpload';


const Main = () => {

  return (
    <>
        <div>
            <FileUpload />
        </div>
        <div className="Left-Layer">
            <StandardProducts />
        </div>
       
        <div className="Right-Layer">
            <PartnerProducts />
        </div>
    </>
  )
}
export default Main;
