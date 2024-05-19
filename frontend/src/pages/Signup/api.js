import axios from "axios";

export function signUp(body) {
    return axios.post("http://localhost:8080/api/v1/users",body);
}