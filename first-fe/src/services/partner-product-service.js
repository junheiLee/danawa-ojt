import axios from "axios";

export const download = async (page, category, searchCode, searchName) => {

    try {

        const { data, headers } = await axios.get(`/partner-products/download-excel?page=${page}&category=${category}&searchName=${searchName}&searchCode=${searchCode}`, {
            responseType: 'blob',
        });
        
        const blob = new Blob([data], {
            typ: headers[`content-type`]
        });

        const blobUrl = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = blobUrl;
        link.download = `협력사상품.xlsx`;
        link.click();
        URL.revokeObjectURL(blobUrl);

    } catch(error) {
        alert(error.response.data.message);
    }
}