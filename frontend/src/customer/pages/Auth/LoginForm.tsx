
/* eslint-disable @typescript-eslint/no-explicit-any */
import { Button, CircularProgress, TextField } from '@mui/material'
import { useEffect, useState } from 'react'
import OTPInput from '../../components/OtpFild/OTPInput'
import { useFormik } from 'formik'
import { useAppDispatch, useAppSelector } from '../../../Redux Toolkit/Store'
import { useNavigate } from 'react-router-dom'
import {
    sendLoginSignupOtp,
    signin
} from '../../../Redux Toolkit/Customer/AuthSlice'

const LoginForm = () => {
    const navigate = useNavigate()
    const [otp, setOtp] = useState('')
    const [timer, setTimer] = useState<number>(30)
    const [isTimerActive, setIsTimerActive] = useState<boolean>(false)

    const dispatch = useAppDispatch()
    const { auth } = useAppSelector(store => store)

    const formik = useFormik({
        initialValues: {
            email: '',
            otp: ''
        },

        onSubmit: (values: any) => {
            dispatch(signin({ email: values.email, otp, navigate }))
            console.log('Form data:', values)
        }
    })

    const handleOtpChange = (otp: any) => {
        setOtp(otp)
    }

    const handleResendOTP = () => {
        dispatch(
            sendLoginSignupOtp({
                email: 'signing_' + formik.values.email
            })
        )

        console.log('Resend OTP')
        setTimer(30)
        setIsTimerActive(true)
    }

    const handleSentOtp = () => {
        handleResendOTP()
    }

    const handleLogin = () => {
        formik.handleSubmit()
    }

    useEffect(() => {
        let interval: ReturnType<typeof setInterval> | undefined

        if (isTimerActive) {
            interval = setInterval(() => {
                setTimer(prev => {
                    if (prev === 1) {
                        if (interval) clearInterval(interval)
                        setIsTimerActive(false)
                        return 30
                    }

                    return prev - 1
                })
            }, 1000)
        }

        return () => {
            if (interval) clearInterval(interval)
        }
    }, [isTimerActive])

    return (
        <div>
            <div className="pb-6">
                <h1 className="text-2xl font-bold text-gray-900">
                    Login to your account
                </h1>
                <p className="text-sm text-gray-500 mt-1">
                    Enter your email and we'll send you a one-time code
                </p>
            </div>

            <form
                className="space-y-5"
                onSubmit={e => {
                    e.preventDefault()
                    if (auth.otpSent) {
                        handleLogin()
                    } else {
                        handleSentOtp()
                    }
                }}
            >
                <TextField
                    fullWidth
                    name="email"
                    type="email"
                    label="Email address"
                    value={formik.values.email}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    error={
                        formik.touched.email &&
                        Boolean(formik.errors.email)
                    }
                    helperText={
                        formik.touched.email
                            ? (formik.errors.email as string)
                            : undefined
                    }
                />

                {auth.otpSent && (
                    <div className="space-y-2">
                        <p className="font-medium text-sm text-gray-700">
                            Enter the 6-digit OTP sent to your email
                        </p>

                        <OTPInput
                            length={6}
                            onChange={handleOtpChange}
                            error={false}
                        />

                        <p className="text-xs space-x-2">
                            {isTimerActive ? (
                                <span>
                                    Resend OTP in {timer} seconds
                                </span>
                            ) : (
                                <>
                                    Didn't receive OTP?{' '}
                                    <span
                                        onClick={handleResendOTP}
                                        className="text-teal-600 cursor-pointer hover:text-teal-800 font-semibold"
                                    >
                                        Resend OTP
                                    </span>
                                </>
                            )}
                        </p>

                        {formik.touched.otp && formik.errors.otp && (
                            <p className="text-sm text-red-500">
                                {formik.errors.otp as string}
                            </p>
                        )}
                    </div>
                )}

                {auth.otpSent && (
                    <div>
                        <Button
                            type="submit"
                            disabled={auth.loading}
                            fullWidth
                            variant="contained"
                            sx={{ py: '11px' }}
                        >
                            {auth.loading ? (
                                <CircularProgress
                                    size={24}
                                    color="inherit"
                                />
                            ) : (
                                'Login'
                            )}
                        </Button>
                    </div>
                )}

                {!auth.otpSent && (
                    <Button
                        type="submit"
                        disabled={auth.loading}
                        fullWidth
                        variant="contained"
                        sx={{ py: '11px' }}
                    >
                        {auth.loading ? (
                            <CircularProgress
                                size={24}
                                color="inherit"
                            />
                        ) : (
                            'Send OTP'
                        )}
                    </Button>
                )}
            </form>
        </div>
    )
}

export default LoginForm
