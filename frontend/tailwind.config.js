/*eslint-env node*/
/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts}'],
  theme: {
    background: {
      paper: 'linear-gradient(180deg, #EEEADF 0%, #FFF 100%)'
    },
    extend: {
      fontFamily: {
        sans: ['Lato', ...defaultTheme.fontFamily.sans]
      },
      colors: {
        'custom-blue': '#2790F0',
        'custom-red': '#DB1010',
        'custom-green': '#27891E'
      },
      boxShadow: {
        custom: '0px 4px 4px 0px rgba(0, 0, 0, 0.25)'
      }
    }
  },
  plugins: []
}
