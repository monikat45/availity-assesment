import React, {useState} from 'react';
import './style.css'
function RegistrationForm() {
const [name, setName] = useState('');
    const [npiNumber, setnpiNumber] = useState('');
    const [businessAddress,setbusinessAddress] = useState('');
    const [telephoneNumber,settelephoneNumber] = useState('');
    const [email, setEmail] = useState('');

    const handleInputChange = (e) => {
        const {id , value} = e.target;
        if(id === "name"){
            setName(value);
        }
        if(id === "npiNumber"){
            setnpiNumber(value);
        }
        if(id === "businessAddress"){
            setbusinessAddress(value);
        }
        if(id === "telephoneNumber"){
            settelephoneNumber(value);
        }
        if(id === "email"){
            setEmail(value);
        }

    }

    const handleSubmit  = () => {
        alert("name : " + name + " npiNumber : " + npiNumber + " businessAddress : " + businessAddress + " telephoneNumber : " + 
             telephoneNumber  + " email : "+ email);
        console.log(name,npiNumber,businessAddress,telephoneNumber, email);
    }
    return(
        <div className="form">
            <div className="form-body">
                <div >
                    <label className="form-label" htmlFor="name">First and Last Name </label>
                    <input type="text" className="form-input" value={name} onChange = {(e) => handleInputChange(e)} id="name" placeholder="name"/>
                </div>
                <div >
                    <label className="form-label" htmlFor="npiNumber">NPI Number </label>
                    <input type="number" id="npiNumber" value={npiNumber}  className="form-input" onChange = {(e) => handleInputChange(e)} placeholder="npiNumber"/>
                </div>
                <div >
                    <label className="form-label" htmlFor="businessAddress">Business Address </label>
                    <input className="form-input" type="textarea" id="businessAddress" value={businessAddress} onChange = {(e) => handleInputChange(e)} placeholder="businessAddress"/>
                </div>
                <div >
                    <label className="form-label" htmlFor="telephoneNumber">Telephone Number </label>
                    <input className="form-input" type="text" id="telephoneNumber" value={telephoneNumber} onChange = {(e) => handleInputChange(e)} placeholder="telephoneNumber"/>
                </div>
                <div >
                    <label className="form-label" htmlFor="email">Email </label>
                    <input  type="email" id="email" className="form-input" value={email} onChange = {(e) => handleInputChange(e)} placeholder="Email"/>
                </div>
            </div>
            <div className="footer">
                <button onClick={()=>handleSubmit()} type="submit" className="btn">Register</button>
            </div>
        </div>  
    )       
}
export default RegistrationForm;