import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import en from "./translation/en.json";
import tr from "./translation/tr.json";

const initialLanguage = localStorage.getItem("lang") || navigator.language || "en"

export  const i18nInstance = i18n.use(initReactI18next)
i18nInstance.init({
    
    resources: {
      en: {
        translation: en
      },
      tr: {
        translation: tr
      }
    },

    fallbackLng: initialLanguage,

    interpolation: {
      escapeValue: false 
    
  }});