import axios from "axios";

export const uploadFile = async (option, excelFile) => {

    try {

        const response = await axios.post(`/${option}/excel-upload`, excelFile, {
            headers: {
                "Content-type": "multipart/form-data"
            }
        });

        alert(response.data.message);

    } catch(error) {
        console.error(error);
    }

}