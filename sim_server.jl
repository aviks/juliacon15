using JuliaWebAPI
using Logging
using Logging
Logging.configure(level=DEBUG)


include ("sim.jl")

process([(simulate,false), (simulateTime,false)], "tcp://127.0.0.1:9999"; bind=true)