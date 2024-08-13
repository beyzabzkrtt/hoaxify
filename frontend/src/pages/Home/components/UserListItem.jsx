/* eslint-disable react/prop-types */
// eslint-disable-next-line react/prop-types

import defultProfileImage from "../assets/profile.png";

export function UserListItem({ user }) {
  return (
    <li className="list-group-item list-group-item-action">
        <img src={defultProfileImage}  
        width="30"
        className="img-fluid rounded-circle shadow-sm"/>
        <span className="ms-2">{user.username}</span>
     </li>
  );
}
