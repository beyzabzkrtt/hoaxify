import react from '@vitejs/plugin-react'
import { fileURLToPath, URL } from "node:url"
import { defineConfig } from 'vite'

// https://vitejs.dev/config/

export default defineConfig({
  plugins: [react()],
  server : {
    proxy: {
      "/api" : "http://localhost:8080" ,
    }
  },
  resolve:{
    alias:{
      "@" : fileURLToPath(new URL("./src",import.meta.url))
    }
  }
})
