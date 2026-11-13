import { Box, TextField } from "@mui/material";

// Step 1: Tax Details & Mobile
// Validation (mobile + GSTIN required) SellerAccountForm.tsx ke stepSchemas[0] me hai.
// Jab tak dono sahi na hon, Continue button disabled rahega.

const BecomeSellerFormStep1 = ({ formik }: any) => {
    return (
        <Box>
            <p className="text-xl font-bold text-center pb-9">Contact Details</p>

            <div className="space-y-9">
                <TextField
                    fullWidth
                    name="mobile"
                    label="Mobile"
                    value={formik.values.mobile}
                    // sirf number, max 10 digit
                    onChange={(e) =>
                        formik.setFieldValue("mobile", e.target.value.replace(/\D/g, "").slice(0, 10))
                    }
                    onBlur={formik.handleBlur}
                    inputProps={{ inputMode: "numeric", maxLength: 10 }}
                    error={formik.touched.mobile && Boolean(formik.errors.mobile)}
                    helperText={formik.touched.mobile && formik.errors.mobile}
                />

                <TextField
                    fullWidth
                    name="gstin"
                    label="GSTIN Number"
                    value={formik.values.gstin}
                    // apne aap CAPITAL, space hata deta hai
                    onChange={(e) =>
                        formik.setFieldValue("gstin", e.target.value.toUpperCase().replace(/\s/g, ""))
                    }
                    onBlur={formik.handleBlur}
                    inputProps={{ maxLength: 15 }}
                    error={formik.touched.gstin && Boolean(formik.errors.gstin)}
                    helperText={formik.touched.gstin && formik.errors.gstin}
                />
            </div>
        </Box>
    );
};

export default BecomeSellerFormStep1;