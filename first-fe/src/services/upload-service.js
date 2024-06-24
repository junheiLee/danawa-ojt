import axios from "axios";

export const uploadFile = async (option, excelFile, setLoading) => {

    try {
        setLoading(true);
        const response = await axios.post(`/${option}/upload/excel`, excelFile, {
            headers: {
                "Content-type": "multipart/form-data"
            }
        });
        setLoading(false);
        alert(response.data.message);

    } catch(error) {

        if(error.response.data.resultCode === "NOT_HAVE_DOMAIN") {
            alert(error.response.data.message + option);
        }else {
            alert(error.response.data.message);
        }

    }

}