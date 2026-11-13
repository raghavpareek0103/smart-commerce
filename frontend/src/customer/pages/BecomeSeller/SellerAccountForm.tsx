// import { Button, CircularProgress, Step, StepLabel, Stepper } from "@mui/material";
// import React, { useState } from "react";
// import BecomeSellerFormStep1 from "./BecomeSellerFormStep1";
// import BecomeSellerFormStep3 from "./BecomeSellerFormStep3";
// import BecomeSellerFormStep2 from "./BecomeSellerFormStep2";
// import { useFormik } from "formik";
// import * as Yup from "yup";
// import BecomeSellerFormStep4 from "./BecomeSellerFormStep4";
// import { useDispatch } from "react-redux";
// import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
// import SellerLoginForm from "./SellerLoginForm";
// import { createSeller } from "../../../Redux Toolkit/Seller/sellerAuthenticationSlice";
//
// const steps = [
//     "Tax Details & Mobile",
//     "Pickup Address",
//     "Bank Details",
//     "Supplier Details",
//   ];
//
//
// const SellerAccountForm = () => {
//     const [activeStep, setActiveStep] = useState(0);
//   const dispatch = useAppDispatch();
//   const {sellerAuth}=useAppSelector(store=>store)
//
//   const handleStep = (value: number) => {
//     setActiveStep(activeStep + value);
//   };
//
//   const [otp, setOpt] = useState<any>();
//
//   const formik = useFormik({
//     initialValues: {
//       mobile: "",
//       otp: "",
//       gstin: "",
//       pickupAddress: {
//         name: "",
//         mobile: "",
//         pincode: "",
//         address: "",
//         locality: "",
//         city: "",
//         state: "",
//       },
//       bankDetails: {
//         accountNumber: "",
//         ifscCode: "",
//         accountHolderName: "",
//       },
//       sellerName: "",
//       email: "",
//       businessDetails: {
//         businessName: "",
//         businessEmail:"",
//         businessMobile:"",
//         logo:"",
//         banner:"",
//         businessAddress:""
//       },
//       password: ""
//     },
//     // validationSchema: FormSchema,
//     onSubmit: (values) => {
//       console.log(values, "formik submitted");
//       console.log("active step ", activeStep);
//       dispatch(createSeller(formik.values))
//     },
//   });
//
//   const handleOtpChange = (otpValue: string) => {
//     setOpt(otpValue);
//     console.log(otpValue);
//     // formik.setFieldValue("opt",otpValue)
//   };
//
//   const handleSubmit = () => {
//     //submit form data to server
//     formik.handleSubmit();
//     console.log("Form Submitted");
//   };
//
//
//
//     return (
//         <div>  <Stepper activeStep={activeStep} alternativeLabel>
//             {steps.map((label) => (
//                 <Step key={label}>
//                     <StepLabel>{label}</StepLabel>
//                 </Step>
//             ))}
//         </Stepper>
//             <div className="mt-20 space-y-10">
//                 <div>
//                     {activeStep === 0 ? (
//                         <BecomeSellerFormStep1
//                             formik={formik}
//                             handleOtpChange={handleOtpChange}
//                         />
//                     ) : activeStep === 1 ? (
//                         <BecomeSellerFormStep2 formik={formik} />
//                     ) : activeStep === 2 ? (
//                         <BecomeSellerFormStep3 formik={formik} />
//                     ) : (
//                         <BecomeSellerFormStep4 formik={formik} />
//                     )}
//                 </div>
//
//                 <div className="flex items-center justify-between ">
//                     <Button
//                         disabled={activeStep === 0}
//                         onClick={() => handleStep(-1)}
//                         variant="contained"
//                     >
//                         Back
//                     </Button>
//                     <Button
//                     disabled={sellerAuth.loading}
//                         onClick={
//                             activeStep === steps.length - 1
//                                 ? handleSubmit
//                                 : () => handleStep(1)
//                         }
//                         variant="contained"
//                     >
//                         {activeStep === steps.length - 1 ? sellerAuth.loading ? <CircularProgress size="small"
//                         sx={{ width: "27px", height: "27px" }} /> : "create account" : "Continue"}
//                     </Button>
//                 </div>
//             </div> </div>
//     )
// }
//
// export default SellerAccountForm



import { Button, CircularProgress, Step, StepLabel, Stepper } from "@mui/material";
import { useState } from "react";
import BecomeSellerFormStep1 from "./BecomeSellerFormStep1";
import BecomeSellerFormStep3 from "./BecomeSellerFormStep3";
import BecomeSellerFormStep2 from "./BecomeSellerFormStep2";
import { useFormik } from "formik";
import * as Yup from "yup";
import BecomeSellerFormStep4 from "./BecomeSellerFormStep4";
import { useAppDispatch, useAppSelector } from "../../../Redux Toolkit/Store";
import { createSeller } from "../../../Redux Toolkit/Seller/sellerAuthenticationSlice";

const steps = [
    "Tax Details & Mobile",
    "Pickup Address",
    "Bank Details",
    "Supplier Details",
  ];


// ---- Har step ke liye alag validation (jab tak sahi na ho, Continue band) ----
const stepSchemas = [
  // Step 1: Tax Details & Mobile
  Yup.object({
    mobile: Yup.string()
      .required("Mobile number is required")
      .matches(/^[6-9]\d{9}$/, "Enter a valid 10-digit mobile number"),
    gstin: Yup.string()
      .required("GSTIN is required")
      .matches(
        /^\d{2}[A-Z]{5}\d{4}[A-Z][1-9A-Z]Z[0-9A-Z]$/,
        "Enter a valid 15-character GSTIN (e.g. 22AAAAA0000A1Z5)"
      ),
  }),
  // Step 2: Pickup Address
  Yup.object({
    pickupAddress: Yup.object({
      name: Yup.string().trim().required("Name is required"),
      mobile: Yup.string()
        .required("Mobile number is required")
        .matches(/^[6-9]\d{9}$/, "Enter a valid 10-digit mobile number"),
      pincode: Yup.string()
        .required("Pincode is required")
        .matches(/^\d{6}$/, "Pincode must be 6 digits"),
      address: Yup.string().trim().required("Address is required"),
      locality: Yup.string().trim().required("Locality is required"),
      city: Yup.string().trim().required("City is required"),
      state: Yup.string().trim().required("State is required"),
    }),
  }),
  // Step 3: Bank Details
  Yup.object({
    bankDetails: Yup.object({
      accountNumber: Yup.string()
        .required("Account number is required")
        .matches(/^\d{9,18}$/, "Account number must be 9-18 digits"),
      ifscCode: Yup.string()
        .required("IFSC code is required")
        .matches(/^[A-Z]{4}0[A-Z0-9]{6}$/, "Enter a valid IFSC (e.g. SBIN0001234)"),
      accountHolderName: Yup.string().trim().required("Account holder name is required"),
    }),
  }),
  // Step 4: Supplier Details
  Yup.object({
    businessDetails: Yup.object({
      businessName: Yup.string().trim().required("Business name is required"),
    }),
    sellerName: Yup.string().trim().required("Seller name is required"),
    email: Yup.string().email("Enter a valid email").required("Email is required"),
    password: Yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
  }),
];

// Step ke saare fields ko "touched" banata hai taaki errors dikh jayein
const touchAll = (schema: any): any =>
  Object.fromEntries(
    Object.entries(schema.fields).map(([key, field]: [string, any]) => [
      key,
      field.fields ? touchAll(field) : true,
    ])
  );

const SellerAccountForm = () => {
    const [activeStep, setActiveStep] = useState(0);
  const dispatch = useAppDispatch();
  const {sellerAuth}=useAppSelector(store=>store)

  const handleBack = () => {
    setActiveStep((s) => Math.max(s - 1, 0));
  };

  const [, setOpt] = useState<any>();

  const formik = useFormik({
    initialValues: {
      mobile: "",
      otp: "",
      gstin: "",
      pickupAddress: {
        name: "",
        mobile: "",
        pincode: "",
        address: "",
        locality: "",
        city: "",
        state: "",
      },
      bankDetails: {
        accountNumber: "",
        ifscCode: "",
        accountHolderName: "",
      },
      sellerName: "",
      email: "",
      businessDetails: {
        businessName: "",
        businessEmail:"",
        businessMobile:"",
        logo:"",
        banner:"",
        businessAddress:""
      },
      password: ""
    },
    validationSchema: stepSchemas[activeStep],
    onSubmit: (values) => {
      console.log(values, "formik submitted");
      console.log("active step ", activeStep);
      dispatch(createSeller(formik.values))
    },
  });

  const handleOtpChange = (otpValue: string) => {
    console.log(otpValue);
  };

  // Current step sahi bhara hai ya nahi (Continue button isi se on/off hota hai)
  const isStepValid = stepSchemas[activeStep].isValidSync(formik.values);

  const handleNext = async () => {
    formik.setTouched(touchAll(stepSchemas[activeStep]), false);
    const errors = await formik.validateForm();
    if (Object.keys(errors).length > 0) return; // galat/khaali field -> aage nahi
    formik.setTouched({}, false);
    setActiveStep((s) => s + 1);
  };

  const handleSubmit = () => {
    //submit form data to server
    formik.handleSubmit();
    console.log("Form Submitted");
  };



    return (
        <div>  <Stepper activeStep={activeStep} alternativeLabel>
            {steps.map((label) => (
                <Step key={label}>
                    <StepLabel>{label}</StepLabel>
                </Step>
            ))}
        </Stepper>
            <div className="mt-20 space-y-10">
                <div>
                    {activeStep === 0 ? (
                        <BecomeSellerFormStep1
                            formik={formik}
                            handleOtpChange={handleOtpChange}
                        />
                    ) : activeStep === 1 ? (
                        <BecomeSellerFormStep2 formik={formik} />
                    ) : activeStep === 2 ? (
                        <BecomeSellerFormStep3 formik={formik} />
                    ) : (
                        <BecomeSellerFormStep4 formik={formik} />
                    )}
                </div>

                <div className="flex items-center justify-between ">
                    <Button
                        disabled={activeStep === 0}
                        onClick={handleBack}
                        variant="contained"
                    >
                        Back
                    </Button>
                    <Button
                    disabled={sellerAuth.loading || !isStepValid}
                        onClick={
                            activeStep === steps.length - 1
                                ? handleSubmit
                                : handleNext
                        }
                        variant="contained"
                    >
                        {activeStep === steps.length - 1 ? sellerAuth.loading ? <CircularProgress size="small"
                        sx={{ width: "27px", height: "27px" }} /> : "create account" : "Continue"}
                    </Button>
                </div>
            </div> </div>
    )
}

export default SellerAccountForm