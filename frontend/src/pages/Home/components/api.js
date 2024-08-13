import http from "@/lib/http";

export function loadUsers(page=0) {
  return http.get("/api/v1/users", { params: { page: 0, size: 3 } });
}
