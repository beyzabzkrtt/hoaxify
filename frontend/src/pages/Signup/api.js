import axios from 'axios';
import { i18nInstance } from '../../locales';

export function signUp(body) {
    return axios.post("http://localhost:8080/api/v1/users",body,{
      headers : {
        "Accept-Language" : i18nInstance.language
      }
    })
.then(response => response.data)
.catch(error => {
  console.error("There was an error!", error);
  throw error;
});
}