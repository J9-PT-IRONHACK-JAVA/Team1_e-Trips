const axios = require("axios");

export async function createUser(email, username, password) {
  const payload = {
    username: username,
    password: password,
    email: email,
    roles: "ROLE_USER",
    isAccountNonLocked: true,
  };
  const response = await axios.post(`/user-management`, payload);
  return response.data;
}
